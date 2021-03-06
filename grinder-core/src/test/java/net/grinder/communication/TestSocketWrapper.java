// Copyright (C) 2005 - 2012 Philip Aston
// All rights reserved.
//
// This file is part of The Grinder software distribution. Refer to
// the file LICENSE which is part of The Grinder distribution for
// licensing details. The Grinder distribution is available on the
// Internet at http://grinder.sourceforge.net/
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
// FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
// COPYRIGHT HOLDERS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
// INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
// HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
// OF THE POSSIBILITY OF SUCH DAMAGE.

package net.grinder.communication;

import java.net.InetAddress;
import java.net.Socket;

import net.grinder.util.StandardTimeAuthority;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *  Unit tests for {@link SocketWrapper}.
 *
 * @author Philip Aston
 */
public class TestSocketWrapper {

  private static Acceptor s_acceptor;
  private Socket m_socket;

  @BeforeClass public static void setUpAcceptor() throws Exception {
    s_acceptor = new Acceptor("localhost", 0, 1, new StandardTimeAuthority());
  }

  @AfterClass public static void shutDownAcceptor() throws Exception {
    s_acceptor.shutdown();
  }

  @Before public void createSocket() throws Exception {
    m_socket = new Socket(InetAddress.getByName(null), s_acceptor.getPort());
  }

  @Test(expected=CommunicationException.class)
  public void testConstructionWithBadSocket() throws Exception {
    m_socket.close();
    new SocketWrapper(m_socket);
  }
}
